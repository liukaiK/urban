import schedule from 'node-schedule';

let s = '0 1 * * *'; //凌晨一点执行
let a = '* * * * * *'; //每秒执行一次

const job = schedule.scheduleJob(a, function () {
    console.log('现在是凌晨一点！');
});
