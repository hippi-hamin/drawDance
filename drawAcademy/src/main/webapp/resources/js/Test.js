const date = new Date();

const viewYear = date.getFullYear();
const viewMonth = date.getMonth();
document.querySelector('.year-month').textContent = `${viewYear}년 ${viewMonth + 1}월`;