import React from "react";
import ReactDOM from "react-dom";
import configureStore from "./main-page-content/store/configureStore"
import { Provider } from 'react-redux'
import App from "./main-page-content/containers/App";


const store = configureStore()
const root = document.getElementById("main-page-container");



export function mainRender() {
    ReactDOM.render (
        <div>
            <Provider store={store}>
                <App/>
            </Provider>
        </div> ,
        root
    );
}

store.subscribe(mainRender)
mainRender()


