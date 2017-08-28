import React from "react";
import ReactDOM from "react-dom";
import SignIn from "./start-page-content/components/SignIn";
import Header from "./start-page-content/components/Header";

const root1 = document.getElementById("container1");

function render1() {
    ReactDOM.render (
        <div>
            <Header/>
            <SignIn/>
        </div>,
        root1
    );
}

render1()
