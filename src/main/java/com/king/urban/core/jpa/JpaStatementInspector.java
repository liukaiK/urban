package com.king.urban.core.jpa;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import com.king.urban.common.constant.SysConstants;
import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.Parenthesis;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.*;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.statement.update.Update;
import org.hibernate.resource.jdbc.spi.StatementInspector;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 数据权限的拦截器 以部门为维度
 *
 * @author liukai
 */
@Slf4j
public class JpaStatementInspector implements StatementInspector, ServletContainerInitializer {

    private Long deptId;

    private List<Long> deptIds;

    private List<String> deptTables = new ArrayList<>();


    private final String deptIdColumn = "dept_id";


    /**
     * 重写StatementInspector的inspect接口，参数为hibernate处理后的原始SQL，返回值为我们修改后的SQL
     *
     * @param sql
     * @return
     */
    @Override
    public String inspect(String sql) {
//        try {
        /**
         * 未登录用户，系统用户不做解析
         */
//            CurrentUser current = UserContext.current();
//            if (UserContext.current() == null || UserContext.current().getAdministrator()) {
//                return null;
//            }

        return sql;
//            if (!StpUtil.isLogin()) {
//                return sql;
//            }

        /**
         * 初始化需要进行解析的组织表,
         */
//            if (orgTables == null) {
//                synchronized (JpaStatementInspector.class) {
//                    OrganizationProperties bean = SpringContextUtil.getBean(OrganizationProperties.class);
//                    if (bean != null) {
//                        orgTables = bean.getTables();
//                    } else {
//                        throw new RuntimeException("未能获取TenantProperties参数配置");
//                    }
//                }
//            }

        /**
         * 从当前线程获取登录用户的所属用户组织ID及其子孙组织ID
         */
//            CurrentUser user = UserContext.current();
//            orgId = user.getOrganizationId();
//            orgIds = user.getOrganizationIds();
//            deptTables.add("t_sys_employee");
//
//            deptId = getCurrentDeptId();
//            deptIds = getCurrentChildrenDeptId();
//
//            log.info("组织筛选解析开始，原始SQL：{}", sql);
//            Statements statements = CCJSqlParserUtil.parseStatements(sql);
//            StringBuilder sqlStringBuilder = new StringBuilder();
//            int i = 0;
//            for (Statement statement : statements.getStatements()) {
//                if (null != statement) {
//                    if (i++ > 0) {
//                        sqlStringBuilder.append(';');
//                    }
//                    sqlStringBuilder.append(this.processParser(statement));
//                }
//            }
//            String newSql = sqlStringBuilder.toString();
//            log.info("组织筛选解析结束，解析后SQL：{}", newSql);
//            return newSql;
//        } catch (Exception e) {
//            log.error("组织筛选解析失败，解析SQL异常{}", e.getMessage());
//            e.printStackTrace();
//        } finally {
//            deptId = null;
//        }
//        return null;
    }

    protected String processParser(Statement statement) {
        if (statement instanceof Insert) {
            this.processInsert((Insert) statement);
        } else if (statement instanceof Select) {
            this.processSelectBody(((Select) statement).getSelectBody());
        } else if (statement instanceof Update) {
            this.processUpdate((Update) statement);
        } else if (statement instanceof Delete) {
            this.processDelete((Delete) statement);
        }
        /**
         * 返回处理后的SQL
         */
        return statement.toString();
    }

    /**
     * select 语句处理
     */
    public void processSelectBody(SelectBody selectBody) {
        if (selectBody instanceof PlainSelect) {
            processPlainSelect((PlainSelect) selectBody);
        } else if (selectBody instanceof WithItem) {
            WithItem withItem = (WithItem) selectBody;
            if (withItem.getSelectBody() != null) {
                processSelectBody(withItem.getSelectBody());
            }
        } else {
            SetOperationList operationList = (SetOperationList) selectBody;
            if (CollectionUtil.isNotEmpty(operationList.getSelects())) {
                operationList.getSelects().forEach(this::processSelectBody);
            }
        }
    }

    /**
     * insert 语句处理
     */
    public void processInsert(Insert insert) {
        if (deptTables.contains(insert.getTable().getFullyQualifiedName())) {
            insert.getColumns().add(new Column(deptIdColumn));
            if (insert.getSelect() != null) {
                processPlainSelect((PlainSelect) insert.getSelect().getSelectBody(), true);
            } else if (insert.getItemsList() != null) {
                // fixed github pull/295
                ItemsList itemsList = insert.getItemsList();
                if (itemsList instanceof MultiExpressionList) {
                    ((MultiExpressionList) itemsList).getExprList().forEach(el -> el.getExpressions().add(new LongValue(deptId)));
                } else {
                    ((ExpressionList) insert.getItemsList()).getExpressions().add(new LongValue(deptId));
                }
            } else {
                throw new RuntimeException("Failed to process multiple-table update, please exclude the tableName or statementId");
            }
        }
    }

    /**
     * update 语句处理
     */
    public void processUpdate(Update update) {
        final Table table = update.getTable();
        if (deptTables.contains(table.getFullyQualifiedName())) {
            update.setWhere(this.andExpression(table, update.getWhere()));
        }
    }

    /**
     * delete 语句处理
     */
    public void processDelete(Delete delete) {
        if (deptTables.contains(delete.getTable().getFullyQualifiedName())) {
            delete.setWhere(this.andExpression(delete.getTable(), delete.getWhere()));
        }
    }

    /**
     * delete update 语句 where 处理
     */
    protected BinaryExpression andExpression(Table table, Expression where) {
        // 获得where条件表达式
        EqualsTo equalsTo = new EqualsTo();
        equalsTo.setLeftExpression(this.getAliasColumn(table));
        equalsTo.setRightExpression(new LongValue(deptId));
        if (null != where) {
            if (where instanceof OrExpression) {
                return new AndExpression(equalsTo, new Parenthesis(where));
            } else {
                return new AndExpression(equalsTo, where);
            }
        }
        return equalsTo;
    }

    /**
     * 处理 PlainSelect
     */
    protected void processPlainSelect(PlainSelect plainSelect) {
        if (plainSelect.getWhere() != null) {
            processPlainSelect(plainSelect, true);
        } else {
            processPlainSelect(plainSelect, false);
        }

    }

    /**
     * 处理 PlainSelect
     *
     * @param plainSelect ignore
     * @param addColumn   是否添加租户列,insert into select语句中需要
     */
    protected void processPlainSelect(PlainSelect plainSelect, boolean addColumn) {
        FromItem fromItem = plainSelect.getFromItem();
        if (fromItem instanceof Table) {
            Table fromTable = (Table) fromItem;
            if (deptTables.contains(fromTable.getFullyQualifiedName())) {
                //#1186 github
                plainSelect.setWhere(builderExpression(plainSelect.getWhere(), fromTable));
                if (addColumn) {
                    plainSelect.getSelectItems().add(new SelectExpressionItem(new Column(deptIdColumn)));
                }
            }
        } else {
            processFromItem(fromItem);
        }
        List<Join> joins = plainSelect.getJoins();
        if (CollectionUtil.isNotEmpty(joins)) {
            joins.forEach(j -> {
                processJoin(j);
                processFromItem(j.getRightItem());
            });
        }
    }

    /**
     * 处理子查询等
     */
    protected void processFromItem(FromItem fromItem) {
        if (fromItem instanceof SubJoin) {
            SubJoin subJoin = (SubJoin) fromItem;
            if (subJoin.getJoinList() != null) {
                subJoin.getJoinList().forEach(this::processJoin);
            }
            if (subJoin.getLeft() != null) {
                processFromItem(subJoin.getLeft());
            }
        } else if (fromItem instanceof SubSelect) {
            SubSelect subSelect = (SubSelect) fromItem;
            if (subSelect.getSelectBody() != null) {
                processSelectBody(subSelect.getSelectBody());
            }
        } else if (fromItem instanceof ValuesList) {
            log.debug("Perform a subquery, if you do not give us feedback");
        } else if (fromItem instanceof LateralSubSelect) {
            LateralSubSelect lateralSubSelect = (LateralSubSelect) fromItem;
            if (lateralSubSelect.getSubSelect() != null) {
                SubSelect subSelect = lateralSubSelect.getSubSelect();
                if (subSelect.getSelectBody() != null) {
                    processSelectBody(subSelect.getSelectBody());
                }
            }
        }
    }

    /**
     * 处理联接语句
     */
    protected void processJoin(Join join) {
        if (join.getRightItem() instanceof Table) {
            Table fromTable = (Table) join.getRightItem();
            if (deptTables.contains(fromTable.getFullyQualifiedName())) {
                join.setOnExpression(builderExpression(join.getOnExpression(), fromTable));
            }
        }
    }

    /**
     * 处理条件:
     * 创建InExpression，即封装where orgId in ('','')
     */
    protected Expression builderExpression(Expression currentExpression, Table table) {
        final InExpression organizationExpression = new InExpression();
        List<Expression> expressions = new ArrayList<>();
        deptIds.forEach(organizatinId -> expressions.add(new LongValue(organizatinId)));
        ExpressionList expressionList = new ExpressionList(expressions);
        organizationExpression.setLeftExpression(this.getAliasColumn(table));
        organizationExpression.setRightItemsList(expressionList);

        Expression appendExpression = null;
        if (!(organizationExpression instanceof SupportsOldOracleJoinSyntax)) {
            appendExpression = new EqualsTo();
            ((EqualsTo) appendExpression).setLeftExpression(this.getAliasColumn(table));
            ((EqualsTo) appendExpression).setRightExpression(organizationExpression);
        }
        if (currentExpression == null) {
            return organizationExpression;
        } else {
            appendExpression = organizationExpression;
        }
        if (currentExpression instanceof BinaryExpression) {
            BinaryExpression binaryExpression = (BinaryExpression) currentExpression;
            doExpression(binaryExpression.getLeftExpression());
            doExpression(binaryExpression.getRightExpression());
        } else if (currentExpression instanceof InExpression) {
            InExpression inExp = (InExpression) currentExpression;
            ItemsList rightItems = inExp.getRightItemsList();
            if (rightItems instanceof SubSelect) {
                processSelectBody(((SubSelect) rightItems).getSelectBody());
            }
        }
        if (currentExpression instanceof OrExpression) {
            return new AndExpression(new Parenthesis(currentExpression), appendExpression);
        } else {
            return new AndExpression(currentExpression, appendExpression);
        }
    }

    protected void doExpression(Expression expression) {
        if (expression instanceof FromItem) {
            processFromItem((FromItem) expression);
        } else if (expression instanceof InExpression) {
            InExpression inExp = (InExpression) expression;
            ItemsList rightItems = inExp.getRightItemsList();
            if (rightItems instanceof SubSelect) {
                processSelectBody(((SubSelect) rightItems).getSelectBody());
            }
        }
    }


    /**
     * 租户字段别名设置
     * <p>tableName.orgId 或 tableAlias.orgId</p>
     *
     * @param table 表对象
     * @return 字段
     */
    protected Column getAliasColumn(Table table) {
        StringBuilder column = new StringBuilder();
        if (null == table.getAlias()) {
            column.append(table.getName());
        } else {
            column.append(table.getAlias().getName());
        }
        column.append(".");
        column.append(deptIdColumn);
        return new Column(column.toString());
    }


    /**
     * 获取当前登录人的部门ID
     */
    private Long getCurrentDeptId() {
        return Convert.convert(Long.class, StpUtil.getSession().get(SysConstants.SESSION_CURRENT_DEPT_ID));
    }

    private List<Long> getCurrentChildrenDeptId() {
        return Convert.toList(Long.class, StpUtil.getSession().get(SysConstants.SESSION_CURRENT_CHILDREN_DEPT_ID));
    }

    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        log.info("初始化");
    }
}
