import React from "react";
import ReactDOM from "react-dom";
import "./index.css";
import {
  Route,
  Link,
  HashRouter as Router,
  Switch,
  Redirect,
} from "react-router-dom";
import { Container, Navbar, Nav, Button } from "react-bootstrap";
import NotFound from "./components/NotFound";
import { logout } from "./services/auth";
import Login from "./components/Login/Login";
import Home from "./components/Home";
import Igraci from "./components/Igraci/Igraci";
import Add from "./components/Igraci/Add";
import Transfer from "./components/Igraci/Transfer";
import EditPlayer from "./components/Igraci/Edit";

class App extends React.Component {
  render() {
    const jwt = window.localStorage["jwt"];

    if (jwt) {
      return (
        <div>
          <Router>
            <Navbar bg="dark" variant="dark" expand>
              <Navbar.Brand as={Link} to="/">
                JWD
              </Navbar.Brand>
              {/*className="mr-auto" podesi ovu grupu Nav Link-ova da se "rasire" sto je vise moguce,
              i zbog toga je dugme Log in/Log out skroz sa leve strane*/}
              <Nav className="mr-auto">
                <Nav.Link as={Link} to="/igraci">
                  Igraci
                </Nav.Link>
              </Nav>

              {window.localStorage["jwt"] ? (
                <Button onClick={() => logout()}>Log out</Button>
              ) : (
                <Nav.Link as={Link} to="/login">
                  Log in
                </Nav.Link>
              )}
            </Navbar>
            <Container style={{ marginTop: 25 }}>
              <Switch>
                <Route exact path="/" component={Home} />
                <Route exact path="/igraci" component={Igraci} />
                <Route exact path="/igraci/add" component={Add} />
                <Route exact path="/igraci/:id" component={EditPlayer} />
                <Route exact path="/igraci/transfer/:id" component={Transfer} />
                <Route exact path="/login" component={Login} />
                <Route component={NotFound} />
              </Switch>
            </Container>
          </Router>
        </div>
      );
    } else {
      return (
        <Container>
          <Router>
            <Switch>
              <Route exact path="/login" component={Login} />
              <Route render={() => <Redirect to="/login" />} />
            </Switch>
          </Router>
        </Container>
      );
    }
  }
}

ReactDOM.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
  document.getElementById("root")
);
