import React from "react";
import FrontAxios from "./../../apis/FrontAxios";
import { Row, Col, Button, Form } from "react-bootstrap";

class Add extends React.Component {
  constructor(props) {
    super(props);

    let igrac = {
      ime: "",
      pozicija: 0,
      brojDresa: "",
      datum: "",
      naProdaju: false,
      klub: null,
    };

    this.state = { igrac: igrac, klubovi: [] };
  }

  componentDidMount() {
    this.getKlubovi();
  }

  async getKlubovi() {
    try {
      let result = await FrontAxios.get("/klubovi");
      let klubovi = result.data;
      this.setState({ klubovi: klubovi });
      console.log("test1");
    } catch (error) {
      console.log(error);
      alert("Couldn't fetch movies");
    }
  }

  async create(e) {
    e.preventDefault();

    try {
      let igrac = this.state.igrac;
      let igracDTO = {
        ime: igrac.ime,
        pozicija: igrac.pozicija,
        brojDresa: igrac.brojDresa,
        datum: igrac.datum,
        naProdaju: igrac.naProdaju,
        klub: igrac.klub,
      };

      let response = await FrontAxios.post("/igraci", igracDTO);
      alert("Igrac je uspesno sacuvan");
      this.props.history.push("/igraci");
    } catch (error) {
      alert("Couldn't save the player");
    }
  }

  valueInputChanged(e) {
    let input = e.target;

    let name = input.name;
    let value = input.value;

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

  render() {
    return (
      <>
        {window.localStorage["role"] == "ROLE_ADMIN"
          ? [
              <Row>
                <Col></Col>
                <Col xs="12" sm="10" md="8">
                  <h1>Dodaj igraca</h1>

                  <Form>
                    <Form.Group>
                      <Form.Label htmlFor="pIme">
                        Ime i prezime igraca
                      </Form.Label>
                      <Form.Control
                        id="pIme"
                        name="ime"
                        onChange={(e) => this.valueInputChanged(e)}
                      />{" "}
                      <br />
                    </Form.Group>
                    <Form.Group>
                      <Form.Label htmlFor="pPozicija">Pozicija</Form.Label>
                      <Form.Control
                        name="pozicija"
                        as="select"
                        id="pPozicija"
                        onChange={(e) => this.valueInputChanged(e)}
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
                        onChange={(e) => this.valueInputChanged(e)}
                      />{" "}
                      <br />
                    </Form.Group>

                    <Form.Group>
                      <Form.Label id="pDatum">Datum rodjenja</Form.Label>
                      <Form.Control
                        type="date"
                        id="pDatum"
                        name="datum"
                        onChange={(e) => this.valueInputChanged(e)}
                      />{" "}
                      <br />
                    </Form.Group>

                    <Form.Group style={{ marginTop: 35 }}>
                      <Form.Check
                        type="checkbox"
                        label="Na prodaju"
                        name="naProdaju"
                        onChange={(event) => this.naProdaju(event)}
                      />
                    </Form.Group>

                    <Form.Group>
                      <Form.Label htmlFor="pKlub">Klub</Form.Label>
                      <Form.Control
                        name="klub"
                        as="select"
                        id="pKlub"
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
                        this.create(event);
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

export default Add;
