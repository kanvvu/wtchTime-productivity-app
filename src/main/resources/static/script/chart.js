const ctx2 = document.getElementById('pie-chart-today');
const ctx =document.getElementById('bar-chart-week');

timesInHours = Object.values(times);
categoryName = Object.keys(times);



// console.log(times);
// console.log(Object.values(times));

let colors = []

for(let i = 0; i<categoryName.length;i++) {
    colors.push("#" + Math.floor(Math.random()*16777215).toString(16))
}

const huj = new Chart(ctx, {
  type: 'bar',
  data: {
    labels: ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"],
    datasets: [{
      label: 'hours',
      data: weekTime,
      borderWidth: 1
    }]
  },
  options: {
    scales: {
      y: {
        beginAtZero: true
      }
    }
  }
});

new Chart(ctx2, { 
type: 'pie',
data: {
    labels: categoryName,
    datasets: [{
      label: 'hours',
      data: timesInHours,
      backgroundColor: colors,
    }]
},
options: {
  responsive: true,
  plugins: {
    legend: {
      position: 'top',
    },
  }
},
});