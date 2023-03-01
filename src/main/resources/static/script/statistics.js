document.getElementById('date-form-submit').addEventListener('click', () => {
    var week; 
    var year;

    if (document.getElementById('weekSelected') != null) {
        week = document.getElementById('weekSelected').value
    } else {
        week = document.getElementById('week1').value
    }

    if (document.getElementById('yearSelected') != null) {
        year = document.getElementById('yearSelected').value
    } else {
        year = document.getElementById('year1').value
    }

    document.getElementById('form-week').value = week
    document.getElementById('form-year').value = year

    
    
});

document.getElementById('week-form-submit').addEventListener('click', () => {
    var year;

    if (document.getElementById('yearSelected') != null) {
        year = document.getElementById('yearSelected').value
    } else {
        year = document.getElementById('year1').value
    }

    document.getElementById('week-form-year').value = year

    
    
});