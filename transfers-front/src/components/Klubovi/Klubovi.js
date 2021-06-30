import React from "react";
import FrontAxios from "../../apis/FrontAxios";
import { Form, Button, Table, Collapse, ButtonGroup } from "react-bootstrap";

class Klubovi extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      klubovi: [],

      search: { naziv: "", klubNaziv: "" },
      pageNo: 0,
      totalPages: 0,
    };
  }

  componentDidMount() {
    this.getKlubovi(0);
  }

  getKlubovi(pageNo) {
    let config = {
      params: {
        pageNo: pageNo,
      },
    };
    FrontAxios.get("/klubovi", config)
      .then((res) => {
        console.log(res);
        this.setState({
          klubovi: res.data,
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

  delete(klubId) {
    FrontAxios.delete("/klubovi/" + klubId)
      .then((res) => {
        // handle success
        console.log(res);
        alert("Club was deleted successfully!");
        window.location.reload();
      })
      .catch((error) => {
        // handle error
        console.log(error);
        alert("Error occured please try again!");
      });
  }

  goToAdd() {
    this.props.history.push("/klubovi/add");
  }

  goToDetails(klubId) {
    this.props.history.push("/klubovi/" + klubId);
  }

  renderKlubovi() {
    return this.state.klubovi.map((klub, index) => {
      return (
        <tr key={klub.id}>
          <td>{klub.naziv}</td>
          <td>{klub.budzet} $</td>
          <td>
            <Button size="sm" onClick={() => this.goToDetails(klub.id)}>
              Igraci
            </Button>
          </td>
        </tr>
      );
    });
  }

  render() {
    return (
      <div>
        <h1>Klubovi</h1>
        <ButtonGroup style={{ marginTop: 25, float: "right" }}>
          <Button
            style={{ margin: 3, width: 90 }}
            disabled={this.state.pageNo == 0}
            onClick={() => this.getKlubovi(this.state.pageNo - 1)}
          >
            Previous
          </Button>
          <Button
            style={{ margin: 3, width: 90 }}
            disabled={this.state.pageNo == this.state.totalPages - 1}
            onClick={() => this.getKlubovi(this.state.pageNo + 1)}
          >
            Next
          </Button>
        </ButtonGroup>
        <Table striped bordered hover variant="dark">
          <thead className="thead-dark">
            <tr>
              <th>Ime kluba</th>
              <th>Budzet kluba</th>
              <th>Igraci</th>
            </tr>
          </thead>
          <tbody>{this.renderKlubovi()}</tbody>
        </Table>
      </div>
    );
  }
}

export default Klubovi;
