import {_api, _requestOptionsPOST} from "./apiRequestHelpers";

const postGraphImage = async (formData) => {
    return await fetch(`${_api}graph/post-image`, _requestOptionsPOST(formData))
        .then(response => response.json());
}

export {
    postGraphImage
}