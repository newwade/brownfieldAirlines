const btn_submit = document.querySelector(".btn-book")
const passenger_form = document.querySelectorAll(".passenger_form")
const trip_summary = document.querySelector(".trip_summary")

const handleSubmit = async() =>{
    const id = trip_summary.getAttribute("data-flight-id")
    const request ={
    flightId:id,
    passengers:[]
    }
    passenger_form.forEach(form => {
        const passenger={
        }
        passenger.firstName=form.passenger_firstname.value;
        passenger.lastName=form.passenger_lastname.value;
        passenger.mobileNumber=form.passenger_phone.value;
        passenger.emailAddress=form.passenger_email.value;
        passenger.gender=form.passenger_gender.value;
        request.passengers.push(passenger);
    })

    const settings = {
        method: 'POST',
        body: JSON.stringify(request),
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json',
        }
    };
    try {
        const fetchResponse = await fetch("http://localhost:8081/api/v1/book/save", settings);
        const data = await fetchResponse.json();
        console.log(data);
//        return data;
    } catch (e) {
        return e;
    }
}

btn_submit.addEventListener("click",handleSubmit)