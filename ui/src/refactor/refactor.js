// invoice 账单
// audience 观众
// tragedy 悲剧
// comedy 喜剧
// seats 座位
// performances 业绩
// render 提供

let invoice = {
    "customer": "BigCo",
    "performances": [
        {
            "playID": "hamlet",
            "audience": 55
        },
        {
            "playID": "as-like",
            "audience": 35
        },
        {
            "playID": "othello",
            "audience": 40
        }
    ]
};


let plays = {
    "hamlet": {"name": "Hamlet", "type": "tragedy"},
    "as-like": {"name": "As You Like It", "type": "comedy"},
    "othello": {"name": "Othello", "type": "tragedy"}
};


function volumeCreditsFor(performance) {
    let result = 0;
    // add volume credits
    result += Math.max(performance.audience - 30, 0);
    // add extra credit for every ten comedy attendees
    if ("comedy" === plays[performance.playID].type) {
        result += Math.floor(performance.audience / 5);
    }
    return result;
}

function usd(aNumber) {
    return new Intl.NumberFormat("en-US",
        {
            style: "currency", currency: "USD",
            minimumFractionDigits: 2
        }).format(aNumber);
}


function statement(invoice, plays) {
    const statementData = {};
    statementData.customer = invoice.customer;
    statementData.performances = invoice.performances.map(enrichPerformance);
    return renderPlainText(statementData, plays);

    function enrichPerformance(aPerformance) {
        return Object.assign({}, aPerformance);
    }
}

function renderPlainText(data, plays) {
    let result = `Statement for ${data.customer}\n`;
    for (let performance of data.performances) {
        // print line for this order
        result += ` ${plays[performance.playID].name}: ${usd(amountFor(performance) / 100)} (${performance.audience} seats)\n`;
    }
    result += `Amount owed is ${usd(totalAmount() / 100)}\n`;
    result += `You earned ${(totalVolumeCredits())} credits\n`;
    return result;
}

function totalAmount() {
    let result = 0;
    for (let performance of invoice.performances) {
        result += amountFor(performance);
    }
    return result;
}

function totalVolumeCredits() {
    let volumeCredits = 0;
    for (let performance of invoice.performances) {
        volumeCredits = volumeCreditsFor(performance);
    }
    return volumeCredits;
}

function amountFor(performance) {
    let result = 0;
    switch (plays[performance.playID].type) {
        case "tragedy":
            result = 40000;
            if (performance.audience > 30) {
                result += 1000 * (performance.audience - 30);
            }
            break;
        case "comedy":
            result = 30000;
            if (performance.audience > 20) {
                result += 10000 + 500 * (performance.audience - 20);
            }
            result += 300 * performance.audience;
            break;
        default:
            throw new Error(`unknown type: ${plays[performance.playID].type}`);
    }
    return result;
}

console.log(statement(invoice, plays));
