import React from "react";
import ReactDOM from "react-dom";
import SignIn from "./start-page-content/components/SignIn";
import Header from "./start-page-content/components/Header";
import MainPageContent from "./start-page-content/containers/MainPageContent";

const root1 = document.getElementById("start-page-container");

export  function startRender() {
    ReactDOM.render (
        <div>
            <MainPageContent/>
        </div>,
        root1
    );
}

startRender()