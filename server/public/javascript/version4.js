'use strict';

const ce = React.createElement;

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
