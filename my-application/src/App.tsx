//import React from 'react';
//import logo from './logo.svg';
import './App.css';
import Navigation from './Components/Navigation';
import LoginForm from './Components/LoginForm';
import Register from './Components/Register';
import { useState } from 'react';

function App() {
  const [isRegisterVisible, setIsRegisterVisible] = useState(false);
  function setRegisterClick(){
    setIsRegisterVisible(!isRegisterVisible);
  }
  return (
    <div>
      <Navigation isRegisterVisible = {isRegisterVisible} onRegisterClick={setRegisterClick}/>
      {isRegisterVisible ? <Register /> : <LoginForm/>}

    </div>
  );
}

export default App;
