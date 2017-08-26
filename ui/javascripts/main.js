import React from "react";
import ReactDOM from "react-dom";
import configureStore from "./main-page-content/store/configureStore"
import { Provider } from 'react-redux'
import App from "./main-page-content/containers/App";

const store = configureStore()
const root = document.getElementById("container");

function render() {
    ReactDOM.render (
        <Provider store={store}>
            <App/>
        </Provider>,
        root
    );
}

render()
store.subscribe(render)


