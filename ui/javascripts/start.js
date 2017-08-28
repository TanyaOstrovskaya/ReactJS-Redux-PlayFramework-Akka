import React from "react";
import ReactDOM from "react-dom";
import SignIn from "./start-page-content/components/SignIn";
import Header from "./start-page-content/components/Header";

const root1 = document.getElementById("start-page-container");

export  function startRender() {
    ReactDOM.render (
        <div>
            <Header/>
            <SignIn/>
        </div>,
        root1
    );
}

startRender()