const _api = "http://localhost:8080/";


const _requestOptionsPOST = (formData, token = "") => {
    const myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    return {
        method: "POST",
        headers: myHeaders,
        body: formData
    }
}


export {
    _api,
    _requestOptionsPOST
}