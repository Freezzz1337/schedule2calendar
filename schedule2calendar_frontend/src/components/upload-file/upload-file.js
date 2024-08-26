import "./upload-file.css";
import {Button, Form} from "react-bootstrap";
import {convertFileToBase64} from "../../util/convert-file-to-base64";
import {useState} from "react";
import {BsCheckCircle, BsUpload, BsXCircle} from "react-icons/bs";
import {postGraphImage} from "../../services/graph-service";

const UploadFile = () => {
    const [graphImage, setGraphImage] = useState(null);
    const [fileName, setFileName] = useState('');
    const [inputKey, setInputKey] = useState(Date.now());
    const [inputDisabled, setInputDisabled] = useState(false);

    const handleChange = (e) => {
        if (graphImage) {
            return;
        }
        if (e.target.type === 'file') {
            const file = e.target.files[0];
            if (file) {
                setFileName(file.name);
                convertFileToBase64(file, (base64String) => {
                    setGraphImage(base64String);
                });
            }
        }
    };

    const handleRemove = () => {
        setGraphImage(null);
        setFileName('');
        setInputDisabled(true);
        setInputKey(Date.now());

        setTimeout(() => {
            setInputDisabled(false);
        }, 100);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        console.log(graphImage);
        try {
            const serverResponse = await postGraphImage(JSON.stringify({graphImage}));
            if (serverResponse.response) {
               console.log("W!")
            } else {
                console.log("L!")
            }
        } catch (error) {
            console.log("Login failed:", error);
        }
    };

    return (
        <Form.Group>
            <Form.Label className="custom-file-upload" htmlFor="file">
                <div className="d-flex align-items-center">
                    {graphImage ? (
                        <div className="d-flex flex-column align-items-center">
                            <span className="mb-2" style={{fontSize: "25px"}}>{fileName}</span>
                            <div className="d-flex justify-content-between w-100">
                                <Button
                                    variant="outline-success"
                                    className="d-flex align-items-center shadow"
                                    onClick={handleSubmit}
                                    style={{marginRight: '10px'}}
                                >
                                    Confirm
                                    <BsCheckCircle size={20} style={{marginLeft: '8px'}}/>
                                </Button>
                                <Button
                                    variant="outline-danger"
                                    className="d-flex align-items-center shadow"
                                    onClick={handleRemove}
                                >
                                    Cancel
                                    <BsXCircle size={20} style={{marginLeft: '8px'}}/>
                                </Button>
                            </div>
                        </div>
                    ) : (
                        <div className="d-flex align-items-center">
                            <BsUpload size={24}/>
                            <div className="ms-2">
                                <span>Click to upload image</span>
                            </div>
                        </div>
                    )}
                </div>
                <Form.Control
                    key={inputKey}
                    disabled={inputDisabled || !!graphImage}
                    type="file"
                    name="picture"
                    accept="image/*"
                    id="file"
                    onChange={handleChange}
                    style={{display: 'none'}}
                />
            </Form.Label>
        </Form.Group>
    );
};


export default UploadFile;