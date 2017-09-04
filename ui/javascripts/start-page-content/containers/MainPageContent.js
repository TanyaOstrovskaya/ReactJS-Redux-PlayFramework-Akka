import Header from "../components/Header";
import SignUp from "../components/SignUp";
import React from 'react';

class MainPageContent extends React.Component {
    render() {
        return (
            <div>
                <Header/>
                <SignUp/>
            </div>
        );
    }
}

export default MainPageContent;