import React from "react";
import ReactDOM from "react-dom";
import SignIn from "./start-page-content/components/SignIn";
import Header from "./start-page-content/components/Header";

const root = document.getElementById("container1");

function render() {
    ReactDOM.render (
        <div>
            <Header/>
            <SignIn/>
        </div>,
        root
    );
}

render()
