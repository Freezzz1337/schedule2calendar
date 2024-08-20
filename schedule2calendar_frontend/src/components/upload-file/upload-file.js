import "./upload-file.css";
import {Button, Form} from "react-bootstrap";
import {convertFileToBase64} from "../../util/convert-file-to-base64";
import {useState} from "react";
import {BsCheckCircle, BsUpload, BsXCircle} from "react-icons/bs";

const UploadFile = () => {
    const [picture, setPicture] = useState(null);
    const [fileName, setFileName] = useState('');

    const handleChange = (e) => {
        if (picture) {
            return;
        }
        if (e.target.type === 'file') {
            const file = e.target.files[0];
            if (file) {
                setFileName(file.name);
                convertFileToBase64(file, (base64String) => {
                    setPicture(base64String);
                });
            }
        }
    };

    const handleRemove = () => {
        setPicture(null);
        setFileName('');
    };

    const handleSubmit = () => {
        console.log('Submitting picture:', picture);
    };
//todo make it so that after pressing cancel the window with the selected file does not open
    return (
        <Form.Group>
            <Form.Label className="custom-file-upload" htmlFor="file">
                <div className="d-flex align-items-center">
                    {picture ? (
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
                    disabled={!!picture}
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