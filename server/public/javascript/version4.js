'use strict';

const ce = React.createElement;

class LoginComponent extends React.Component {
  constructor(props) {
    super(props);
    this.state = {loginName: '', loginPass: '', createName: '', createPass: ''};
  }

  changeHandler(e) {
    console.log(e.target['id']);
  }

  render() {
    return ce('div', null,
      ce('h2', null, 'Login:'),
      ce('br'),
      ce('label', {for: 'loginName'}, 'Username:'),
      ce('input', {id: 'loginName', name: 'loginName', type: 'text', value: this.state.loginName, onChange: e => this.changeHandler(e)}),
      ce('br'),
      ce('label', {for: 'loginPass'}, 'Password:'),
      ce('input', {id: 'loginPass', name: 'loginPass', type: 'password', value: this.state.loginPass, onChange: e => this.changeHandler(e)}),
      ce('br'),
      ce('button', {onClick: e => this.login(e)}, 'Login'),
      // span #login-message
      ce('h2', null, 'Create User:'),
      ce('br'),
      ce('label', {for: 'createName'}, 'Username:'),
      ce('input', {id: 'createName', name: 'createName', type: 'text', value: this.state.createName, onChange: e => this.changeHandler(e)}),
      ce('br'),
      ce('label', {for: 'createPass'}, 'Password:'),
      ce('input', {id: 'createPass', name: 'createPass', type: 'password', value: this.state.createPass, onChange: e => this.changeHandler(e)}),
      ce('br'),
      ce('button', {onClick: e => this.createUser(e)}, 'Create User'),
      // span #create-message
    );
  }
}

ReactDOM.render(
  ce(LoginComponent, null,
    null
  ),
  document.getElementById('react-root')
);
/*
function StatelessHello(props) {
  return ce('div', null, `Hello ${props.toWhat}`);
}

class Hello extends React.Component {
  constructor(props) {
    super(props);
    this.state = { clickCount: 0 };
  }

  clickHandler(e) {
    this.setState({clickCount: this.state.clickCount + 1});
  }

  render() {
    return ce('div', {onClick: (e) => this.clickHandler(e)}, `Hello ${this.props.toWhat} - click count ${this.state.clickCount}`);
  }
}

class SimpleForm extends React.Component {
  constructor(props) {
    super(props);
    this.state = { textInput: '' };
  }

  changeHandler(event) {
    this.setState({textInput: event.target.value});
  }

  render() {
    return ce('input', {type: 'text', value: this.state.textInput, onChange: (e) => this.changeHandler(e)});
  }
}

ReactDOM.render(
  ce('div', null,
    ce(Hello, {toWhat: 'World'}, null),
    ce(StatelessHello, {toWhat: 'World'}, null),
    ce(SimpleForm, null, null),
  ),
  document.getElementById('react-root')
);
*/
