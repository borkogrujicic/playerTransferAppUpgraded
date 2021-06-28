import React from "react";
import FrontAxios from "./../../apis/FrontAxios";
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
    this.getKlubovi();
  }

  getKlubovi() {
    FrontAxios.get("/klubovi")
      .then((res) => {
        console.log(res);
        this.setState({
          klubovi: res.data,
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
        </tr>
      );
    });
  }

  render() {
    return (
      <div>
        <h1>Klubovi</h1>
        <Table bordered striped style={{ marginTop: 5 }}>
          <thead className="thead-dark">
            <tr>
              <th>Ime kluba</th>
              <th>Budzet kluba</th>
            </tr>
          </thead>
          <tbody>{this.renderKlubovi()}</tbody>
        </Table>
      </div>
    );
  }
}

export default Klubovi;
