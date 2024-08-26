import {Container, Row} from "react-bootstrap";
import UploadFile from "../upload-file";

const ConvertPage = () => {

    return (
        <Container fluid >
            <Row className="align-items-center vh-100">
                <UploadFile/>
            </Row>
        </Container>
    )
}
export default ConvertPage;