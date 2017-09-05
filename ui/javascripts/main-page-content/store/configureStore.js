import { createStore, applyMiddleware } from 'redux';
import thunk from 'redux-thunk';
import rootReducer from '../reducers';

const initialState = {
    points: [],
    r: 5
};

export default function configureStore(initialState) {
    const store = createStore(rootReducer, initialState, applyMiddleware(thunk));
    return store;
}