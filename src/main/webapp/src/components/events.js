import React from 'react'
import Table from 'react-bootstrap/Table';
import Image from 'react-bootstrap/Image'
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Alert from 'react-bootstrap/Alert';

const Events = ({ events }) => {
    return (
        <Container fluid>
        <Row>
            <Col><h2>Events</h2></Col>
        </Row>
        <Row>
            <Col>
            {events.length > 0 ? (
            <Table striped bordered hover>
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Name</th>
                        <th>Address</th>
                        <th>City</th>
                        <th>Province</th>
                        <th>Country</th>
                        {/* <th>Date</th> */}
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th>Location</th>
                        <th>Artist</th>
                        {/* <th>Image</th> */}
                    </tr>
                </thead>
                <tbody >
                {events.map((event) => (
                    <tr key={event.id} role="row">
                        <td>{event.id}</td>
                        <td>{event.name}</td>
                        <td>{event.address}</td>
                        <td>{event.city}</td>
                        <td>{event.province}</td>
                        <td>{event.country}</td>
                        {/* <td>{event.date}</td> */}
                        <td><span class="font-monospace">{event.startDate + ' ' + event.startTime}</span></td>
                        <td><span class="font-monospace">{event.endDate + ' ' + event.endTime}</span></td>
                        <td>{event.location}</td>
                        <td>{event.artist}</td>
                        {/* <td ><Image src={'/api/files/' + event.image}/></td> */}
                    </tr>
                ))}
                </tbody>
            </Table>
            ) : 
            (
            <Alert variant='danger'>
            No events!
            </Alert>
            )}
        </Col>
        </Row>
        </Container>
    )
};

export default Events