import React, {Component, useEffect} from 'react';
import Events from './components/events'
import Config from './components/config'
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

const DEFAULT_STATE = {
  events: [],
  config: {
    name: "NO NAME",
    status: {
      status: "UNKNOWN",
      checks: []
    }
  }
}

class App extends Component {
  intervalId;
  constructor(props) {
    super(props)
    this.state = DEFAULT_STATE
  }

  reset = () => {
    this.setState(DEFAULT_STATE)
  }

  fetchData() {
    fetch('/api/events')
    .then(res => res.json())
    .then((data) => {
      this.setState({ events: data })
    })
    .catch(console.log)

    fetch('/api/config')
    .then(res => res.json())
    .then((data) => {
      this.setState({ config: data })
    })
    .catch(console.log)
  }

  componentDidMount() {
    this.fetchData()
    this.intervalId = setInterval(() => {
      this.fetchData()
    }, 2000);
  }

  componentWillUnmount(){
    clearInterval(this.intervalId);
  }

  render () {
    return (
      <Container fluid>
        <Row>
          <Col><h1>Gramola Status</h1></Col>
        </Row>
        <Row>
          <Col><Config config={this.state.config} /></Col>
        </Row>
        <Row>
          <Col><Events events={this.state.events} /></Col>
        </Row>
      </Container>
    );
  }
}

export default App;