'use strict';

const csrfToken = document.getElementById('csrfToken').value;
const validateRoute = document.getElementById('validateRoute').value;
const tasksRoute = document.getElementById('tasksRoute').value;
const addRoute = document.getElementById('addRoute').value;
const createRoute = document.getElementById('createRoute').value;
const deleteRoute = document.getElementById('deleteRoute').value;
const logoutRoute = document.getElementById('logoutRoute').value;
const ce = React.createElement;

class Version4MainComponent extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      loggedIn: false,
    };
  }

  render() {
    if (this.state.loggedIn) {
      return ce(TaskListComponent, { doLogout: () => this.setState({ loggedIn: false })});
    } else {
      return ce(LoginComponent, { doLogin: () => this.setState({ loggedIn: true }) });
    }
  }
}

class LoginComponent extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      loginName: '',
      loginPass: '',
      loginMessage: '',
      createName: '',
      createPass: '',
      createMessage: '',
    };
  }

  changeHandler(e) {
    this.setState({ [e.target['id']]: e.target.value });
  }

  login(e) {
    const username = this.state.loginName;
    const password = this.state.loginPass;

    fetch(validateRoute, {
      method: 'POST',
      headers: {'Content-Type': 'application/json', 'Csrf-Token': csrfToken},
      body: JSON.stringify({ username, password }),
    }).then(res => res.json()).then(data => {
      if (data) {
        this.props.doLogin();
        // document.getElementById('login-section').hidden = true;
        // document.getElementById('task-section').hidden = false;
        // document.getElementById('login-message').innerHTML = '';
        // document.getElementById('create-message').innerHTML = '';
        // loadTasks();
      } else {
        this.setState({ loginMessage: 'Login failed'});
      }
    });
  }

  render() {
    return ce('div', null,
      ce('h2', null, 'Login:'),
      ce('br'),
      ce('label', {htmlFor: 'loginName'}, 'Username:'),
      ce('input', {id: 'loginName', name: 'loginName', type: 'text', value: this.state.loginName, onChange: e => this.changeHandler(e)}),
      ce('br'),
      ce('label', {htmlFor: 'loginPass'}, 'Password:'),
      ce('input', {id: 'loginPass', name: 'loginPass', type: 'password', value: this.state.loginPass, onChange: e => this.changeHandler(e)}),
      ce('br'),
      ce('button', {onClick: e => this.login(e)}, 'Login'),
      ce('span', {id: 'login-message'}, this.state.loginMessage),
      ce('h2', null, 'Create User:'),
      ce('br'),
      ce('label', {htmlFor: 'createName'}, 'Username:'),
      ce('input', {id: 'createName', name: 'createName', type: 'text', value: this.state.createName, onChange: e => this.changeHandler(e)}),
      ce('br'),
      ce('label', {htmlFor: 'createPass'}, 'Password:'),
      ce('input', {id: 'createPass', name: 'createPass', type: 'password', value: this.state.createPass, onChange: e => this.changeHandler(e)}),
      ce('br'),
      ce('button', {onClick: e => this.createUser(e)}, 'Create User'),
      ce('span', {id: 'create-message'}, this.state.createMessage),
    );
  }
}

class TaskListComponent extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      tasks: [],
      newTask: '',
      taskMessage: '',
    };
  }

  componentDidMount() {
    this.loadTasks();
  }

  loadTasks() {
    fetch(tasksRoute)
      .then(res => res.json())
      .then(tasks => this.setState({ tasks }));
  }

  addTask() {
    const task = this.state.newTask;

    fetch(addRoute, {
      method: 'POST',
      headers: {'Content-Type': 'application/json', 'Csrf-Token': csrfToken},
      body: JSON.stringify(task),
    }).then(res => res.json()).then(data => {
      if (data) {
        this.setState({newTask: ''});
        this.loadTasks();
      } else {
        this.setState({taskMessage: 'Failed to add tasks'});
      }
    });
  }

  handleChange(e) {
    this.setState({ newTask: e.target.value });
  }

  handleAddClick(e) {
    this.addTask();
  }

  render() {
    return ce('div', null,
      'Task List',
      ce('br'),
      ce('ul', null,
        this.state.tasks.map((task, index) => ce('li', { key: index }, task)),
      ),
      ce('br'),
      ce('div', null,
        ce('input', {type: 'text', value: this.state.newTask, onChange: e => this.handleChange(e) }),
        ce('button', {onClick: e => this.handleAddClick(e)}, 'Add Task'),
        this.state.taskMessage,
      ),
      ce('br'),
      ce('button', { onClick: e => this.props.doLogout() }, 'Logout'),
    );
  }
}

ReactDOM.render(
  ce(Version4MainComponent),
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
