import React from "react";
import { Form, Row, Col, Button } from "react-bootstrap";
import FrontAxios from "./../../apis/FrontAxios";

class Transfer extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      igracId: -1,
      igracNaziv: "",
      klubId: -1,
      iznos: 0,
      klubovi: [],
      klub: {},
    };
  }

  componentDidMount() {
    this.getIgracById(this.props.match.params.id);
    this.getKlubovi();
  }

  getIgracById(igracId) {
    FrontAxios.get("/igraci/" + igracId)
      .then((res) => {
        // handle success
        console.log(res);
        this.setState({ igracId: res.data.id, igracNaziv: res.data.ime });
      })
      .catch((error) => {
        // handle error
        console.log(error);
        alert("Error occured please try again!");
      });
  }

  async getKlubovi() {
    try {
      let result = await FrontAxios.get("/klubovi");
      let klubovi = result.data;
      this.setState({ klubovi: klubovi });
      console.log("test1");
    } catch (error) {
      console.log(error);
      alert("Couldn't fetch teams");
    }
  }

  iznos = (event) => {
    console.log(event.target.value);

    const { name, value } = event.target;
    console.log(name + ", " + value);

    this.setState((state, props) => ({
      iznos: value,
    }));
  };

  transfer() {
    var params = {
      igracId: this.state.igracId,
      klubId: this.state.klubId,
      iznos: this.state.iznos,
    };

    FrontAxios.post("/transferi/", params)
      .then((res) => {
        // handle success
        console.log(res);
        alert("Transfer was made successfully!");
        this.props.history.push("/igraci");
      })
      .catch((error) => {
        // handle error
        console.log(error);
        alert("Error occured please try again!");
      });
  }

  klubId = (event) => {
    console.log(event.target.value);

    const { name, value } = event.target;
    console.log(name + ", " + value);

    this.setState((state, props) => ({
      klubId: value,
    }));
  };

  klubSelectionChanged(e) {
    // console.log(e);

    let klubId = e.target.value;
    this.setState((klubId = klubId));
  }

  render() {
    return (
      <>
        {window.localStorage["role"] == "ROLE_ADMIN"
          ? [
              <Row>
                <Col></Col>
                <Col xs="12" sm="10" md="8">
                  <h1>Izvrsi transfer za {this.state.igracNaziv}</h1>

                  <Form>
                    <Form.Group>
                      <Form.Label htmlFor="pIznos">Iznos transfera</Form.Label>
                      <Form.Control
                        id="pIme"
                        name="ime"
                        onChange={(e) => this.iznos(e)}
                      />{" "}
                      <br />
                    </Form.Group>

                    <Form.Group>
                      <Form.Label htmlFor="pKlub">Klub</Form.Label>
                      <Form.Control
                        name="klub"
                        as="select"
                        id="pKlub"
                        onChange={(event) => this.klubId(event)}
                      >
                        <option></option>
                        {this.state.klubovi.map((klub) => {
                          return (
                            <option key={klub.id} value={klub.id}>
                              {klub.naziv}
                            </option>
                          );
                        })}
                      </Form.Control>
                      <br />
                    </Form.Group>

                    <Button
                      onClick={(event) => {
                        this.transfer(event);
                      }}
                    >
                      Add
                    </Button>
                  </Form>
                </Col>
                <Col></Col>
              </Row>,
            ]
          : null}
      </>
    );
  }
}

export default Transfer;
