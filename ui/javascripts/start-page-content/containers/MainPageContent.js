import Header from "../components/Header";
import SignUp from "../components/SignUp";
import React from 'react';
import SignIn from "../components/SignIn";

class MainPageContent extends React.Component {
    render() {
        return (
            <div start-grid>
                <div><Header/></div>

                <div></div>

                <div name="sign-up-block">
                    <label>Sign Up</label>
                    <SignUp/>
                </div>

                <div></div>

                <div name="sign-in-block">
                    <label>Sign In</label>
                    <SignIn/>
                </div>
            </div>
        );
    }
}

export default MainPageContent;