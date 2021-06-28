import React from "react";
import FrontAxios from "./../../apis/FrontAxios";
import { Table } from "react-bootstrap";

class Details extends React.Component {
  constructor(props) {
    super(props);

    let klub = {
      ime: "",
      budzet: 0,
    };

    this.state = {
      klub: klub,
      igraci: [],
    };
  }

  componentDidMount() {
    this.getData();
  }

  getData() {
    this.getKlub();
    this.getIgraci();
  }

  async getKlub() {
    try {
      let result = await FrontAxios.get(
        "/klubovi/" + this.props.match.params.id
      );
      if (result && result.status === 200) {
        this.setState({
          klub: result.data,
        });
      }
    } catch (error) {
      alert("Nije uspelo dobavljanje kluba.");
    }
  }

  async getIgraci() {
    try {
      let result = await FrontAxios.get(
        "/klubovi/" + this.props.match.params.id + "/details"
      );
      if (result && result.status === 200) {
        this.setState({
          igraci: result.data,
        });
      }
    } catch (error) {
      alert("Nije uspelo dobavljanje igraca.");
    }
  }

  renderIgraci() {
    return this.state.igraci.map((igrac, index) => {
      return (
        <tr key={igrac.id}>
          <td>{igrac.ime}</td>
          <td>{igrac.pozicija}</td>
          <td>{igrac.datum}</td>
          <td>{igrac.brojDresa}</td>

          <td>{igrac.naProdaju ? "Da" : "Ne"}</td>
        </tr>
      );
    });
  }

  render() {
    return (
      <div>
        <h1>{this.state.klub.naziv}</h1>
        <Table bordered striped style={{ marginTop: 5 }}>
          <thead thead className="thead-dark">
            <tr>
              <th>Ime i prezime igraca</th>
              <th>Pozicija</th>
              <th>Datum rodjenja</th>
              <th>Broj na dresu</th>
              <th>Na prodaju?</th>
            </tr>
          </thead>
          <tbody>{this.renderIgraci()}</tbody>
        </Table>
      </div>
    );
  }
}

export default Details;
