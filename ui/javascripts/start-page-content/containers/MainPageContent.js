import Header from "../components/Header";
import SignIn from "../components/SignIn";
import React from 'react';

class MainPageContent extends React.Component {
    render() {
        return (
            <div>
                <Header/>
                <SignIn/>
            </div>
        );
    }
}

export default MainPageContent;