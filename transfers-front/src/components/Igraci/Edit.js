import React from "react";
import FrontAxios from "./../../apis/FrontAxios";
import { Form, Button, Row, Col } from "react-bootstrap";

class EditPlayer extends React.Component {
  constructor(props) {
    super(props);

    let igrac = {
      ime: "",
      pozicija: 0,
      brojDresa: "",
      datum: "",
      naProdaju: false,
      klub: {},
    };

    this.state = {
      igrac: igrac,
      klubovi: [],
    };
  }

  componentDidMount() {
    this.getData();
  }

  async getData() {
    await this.getIgrac();
    await this.getKlubovi();
  }

  async getIgrac() {
    try {
      let result = await FrontAxios.get(
        "/igraci/" + this.props.match.params.id
      );
      if (result && result.status === 200) {
        this.setState({
          igrac: result.data,
        });
      }
    } catch (error) {
      alert("Nije uspelo dobavljanje igraca.");
    }
  }

  async getKlubovi() {
    try {
      let result = await FrontAxios.get("/klubovi");
      if (result && result.status === 200) {
        this.setState({
          klubovi: result.data,
        });
      }
    } catch (error) {
      alert("Nije uspelo dobavljanje.");
    }
  }

  async doEdit() {
    try {
      await FrontAxios.put(
        "/igraci/" + this.props.match.params.id,
        this.state.igrac
      );
      this.props.history.push("/igraci");
    } catch (error) {
      alert("Nije uspelo čuvanje.");
    }
  }

  valueInputChange(event) {
    let control = event.target;

    let name = control.name;
    let value = control.value;

    let igrac = this.state.igrac;
    igrac[name] = value;

    this.setState({ igrac: igrac });
  }

  klubSelectionChanged(e) {
    // console.log(e);

    let klubId = e.target.value;
    let klub = this.state.klubovi.find((klub) => klub.id == klubId);

    let igrac = this.state.igrac;
    igrac.klub = klub;

    this.setState({ igrac: igrac });
  }

  naProdaju(e) {
    let transferList = true;
    let igrac = this.state.igrac;
    igrac.naProdaju = transferList;

    this.setState({ igrac: igrac });
  }

  handleInputChange(e) {
    
    const checked = e.target.checked
    let igrac = this.state.igrac;

    (checked) ? igrac.naProdaju = true : igrac.naProdaju = false;
    this.setState({igrac: igrac})

  }





  render() {
    return (
      <>
        {window.localStorage["role"] == "ROLE_ADMIN"
          ? [
              <Row>
                <Col></Col>
                <Col xs="12" sm="10" md="8">
                  <h1>Izmeni igraca</h1>

                  <Form>
                    <Form.Group>
                      <Form.Label htmlFor="pIme">
                        Ime i prezime igraca
                      </Form.Label>
                      <Form.Control
                        id="pIme"
                        name="ime"
                        value={this.state.igrac.ime}
                        onChange={(e) => this.valueInputChange(e)}
                      />{" "}
                      <br />
                    </Form.Group>
                    <Form.Group>
                      <Form.Label htmlFor="pPozicija">Pozicija</Form.Label>
                      <Form.Control
                        name="pozicija"
                        as="select"
                        id="pPozicija"
                        value={this.state.igrac.pozicija}
                        onChange={(e) => this.valueInputChange(e)}
                      >
                        <option></option>
                        <option value="Napadac">Napadac</option>
                        <option value="Odbrambeni">Odbrambeni</option>
                        <option value="Golman">Golman</option>
                        <option value="Vezni">Vezni</option>
                      </Form.Control>
                      <br />
                    </Form.Group>

                    <Form.Group>
                      <Form.Label htmlFor="pBrojDresa">Broj dresa</Form.Label>
                      <Form.Control
                        id="pBrojDresa"
                        name="brojDresa"
                        value={this.state.igrac.brojDresa}
                        onClick={(e) => this.valueInputChange(e)}
                      />{" "}
                      <br />
                    </Form.Group>

                    <Form.Group>
                      <Form.Label id="pDatum">Datum rodjenja</Form.Label>
                      <Form.Control
                        type="date"
                        id="pDatum"
                        name="datum"
                        value={this.state.igrac.datum}
                        onChange={(e) => this.valueInputChange(e)}
                      />{" "}
                      <br />
                    </Form.Group>

                    <Form.Group style={{ marginTop: 35 }}>
                      <Form.Check
                        type="checkbox"
                        label="Na prodaju"
                        name="naProdaju"
                        checked={this.state.igrac.naProdaju}
                        onChange={(e) => this.handleInputChange(e)} 
                      />
                    </Form.Group>

                    <Form.Group>
                      <Form.Label htmlFor="pKlub">Klub</Form.Label>
                      <Form.Control
                        name="klub"
                        as="select"
                        id="pKlub"
                        value={this.state.igrac.klub.id}
                        onChange={(event) => this.klubSelectionChanged(event)}
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
                        this.doEdit(event);
                      }}
                    >
                      Edit
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

export default EditPlayer;
