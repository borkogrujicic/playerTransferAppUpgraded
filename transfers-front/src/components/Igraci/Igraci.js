import React from "react";
import FrontAxios from "./../../apis/FrontAxios";
import { Form, Button, Table, Collapse, ButtonGroup } from "react-bootstrap";

class Igraci extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      igraci: [],

      search: { pozicija: "", klubNaziv: "" },
      pageNo: 0,
      totalPages: 0,
    };
  }

  componentDidMount() {
    this.getIgraci(0);
  }

  getIgraci(pageNo) {
    let config = {
      params: {
        pageNo: pageNo,
      },
    };
    if (this.state.search.pozicija != "") {
      config.params.pozicija = this.state.search.pozicija;
    }
    if (this.state.search.klubNaziv != "") {
      config.params.klubNaziv = this.state.search.klubNaziv;
    }
    FrontAxios.get("/igraci", config)
      .then((res) => {
        console.log(res);
        this.setState({
          igraci: res.data,
          totalPages: res.headers["total-pages"],
          pageNo: pageNo,
        });
      })
      .catch((error) => {
        // handle error
        console.log(error);
        alert("Error occured please try again!");
      });
  }

  delete(igracId) {
    FrontAxios.delete("/igraci/" + igracId)
      .then((res) => {
        // handle success
        console.log(res);
        alert("Player was deleted successfully!");
        window.location.reload();
      })
      .catch((error) => {
        // handle error
        console.log(error);
        alert("Error occured please try again!");
      });
  }

  goToAdd() {
    this.props.history.push("/igraci/add");
  }

  goToTransfer(igracId) {
    this.props.history.push("/igraci/transfer/" + igracId);
  }

  goToEdit(igracId) {
    this.props.history.push("/igraci/" + igracId);
  }

  searchValueInputChange(event) {
    let control = event.target;

    let name = control.name;
    let value = control.value;

    let search = this.state.search;
    search[name] = value;

    this.setState({ search: search });
    this.getIgraci(0);
  }

  renderIgraci() {
    return this.state.igraci.map((igrac, index) => {
      return (
        <tr key={igrac.id}>
          <td>{igrac.ime}</td>
          <td>{igrac.pozicija}</td>
          <td>{igrac.brojDresa}</td>
          <td>{igrac.datum}</td>
          <td>{igrac.naProdaju ? "Da" : "Ne"}</td>
          <td>{igrac.klub.naziv}</td>
          {window.localStorage["role"] == "ROLE_ADMIN"
            ? [
                <td>
                  <Button
                    variant="danger"
                    onClick={() => this.delete(igrac.id)}
                  >
                    Delete
                  </Button>
                </td>,
                <td>
                  <Button
                    variant="danger"
                    onClick={() => this.goToEdit(igrac.id)}
                  >
                    Edit
                  </Button>
                </td>,
              ]
            : null}
          {window.localStorage["role"] == "ROLE_ADMIN"
            ? [
                <td>
                  <Button
                    variant="success"
                    disabled={igrac.naProdaju === false}
                    onClick={() => this.goToTransfer(igrac.id)}
                  >
                    Transfer
                  </Button>
                </td>,
              ]
            : null}
        </tr>
      );
    });
  }

  render() {
    return (
      <div>
        <h1>Igraci</h1>
        <Form.Group style={{ marginTop: 35 }}>
          <Form.Check
            type="checkbox"
            label="Show search form"
            onClick={(event) =>
              this.setState({ showSearch: event.target.checked })
            }
          />
        </Form.Group>
        <Collapse in={this.state.showSearch}>
          <Form style={{ marginTop: 10 }}>
            <Form.Group>
              <Form.Label htmlFor="pPozicija">Pozicija</Form.Label>
              <Form.Control
                name="pozicija"
                as="select"
                id="pPozicija"
                onChange={(e) => this.searchValueInputChange(e)}
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
              <Form.Label>Klub</Form.Label>
              <Form.Control
                onChange={(event) => this.searchValueInputChange(event)}
                name="klubNaziv"
                value={this.state.search.klubNaziv}
                as="input"
              ></Form.Control>
            </Form.Group>
            <br></br>
          </Form>
        </Collapse>
        <ButtonGroup style={{ marginTop: 25, float: "right" }}>
          <Button
            style={{ margin: 3, width: 90 }}
            disabled={this.state.pageNo == 0}
            onClick={() => this.getIgraci(this.state.pageNo - 1)}
          >
            Previous
          </Button>
          <Button
            style={{ margin: 3, width: 90 }}
            disabled={this.state.pageNo == this.state.totalPages - 1}
            onClick={() => this.getIgraci(this.state.pageNo + 1)}
          >
            Next
          </Button>
        </ButtonGroup>
        {window.localStorage["role"] == "ROLE_ADMIN"
          ? [<Button onClick={() => this.goToAdd()}>Kreiraj igraca</Button>]
          : null}
        <Table bordered striped style={{ marginTop: 5 }}>
          <thead className="thead-dark">
            <tr>
              <th>Ime i prezime</th>
              <th>Pozicija</th>
              <th>Broj dresa</th>
              <th>Datum rodjenja</th>
              <th>Na prodaju</th>
              <th>Naziv kluba</th>
              {window.localStorage["role"] == "ROLE_ADMIN"
                ? [<th>Brisanje</th>, <th>Edit</th>, <th>Transfer</th>]
                : null}
            </tr>
          </thead>
          <tbody>{this.renderIgraci()}</tbody>
        </Table>
      </div>
    );
  }
}

export default Igraci;
