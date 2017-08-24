import React from "react";
import ReactDOM from "react-dom";
import configureStore from "./main-page-content/store/configureStore"
import { Provider } from 'react-redux'
import App from "./main-page-content/containers/App";

const store = configureStore()

function render() {
    ReactDOM.render (
        <Provider store={store}>
            <App/>
        </Provider>,
        document.getElementById("container")
    );
}

render()
store.subscribe(render)


