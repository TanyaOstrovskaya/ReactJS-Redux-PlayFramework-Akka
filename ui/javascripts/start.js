import React from "react";
import ReactDOM from "react-dom";
import SignIn from "./start-page-content/components/SignIn"
import Header from "./start-page-content/components/Header"

function render() {
    ReactDOM.render (
        <div>
            <Header/>
            <SignIn/>
        </div>,
        document.getElementById("container")
    );
}

render()
