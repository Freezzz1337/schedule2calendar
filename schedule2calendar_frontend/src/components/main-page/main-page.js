import {Button, Col, Container, Row} from "react-bootstrap";
import "./main-page.css";
import {useNavigate} from "react-router-dom";

const MainPage = () => {
    const navigate = useNavigate();

    const handleStart = (e) => {
        e.preventDefault();

        navigate("/convert-page");
    }

    return (
        <Container fluid>
            <Row>
                <Col>
                    <h2 className="text-center mt-2">Welcome to my app)</h2>
                </Col>
                <hr/>
                <Col>
                    <h2>Instruction</h2>
                </Col>
                <Col xs={12} className="text-center">
                    <button className="start-button" onClick={handleStart}>Start!</button>
                </Col>
            </Row>
        </Container>
    );
}

export default MainPage;