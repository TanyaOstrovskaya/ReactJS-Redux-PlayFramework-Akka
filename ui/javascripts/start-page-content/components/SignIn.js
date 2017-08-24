import React from "react";

export default class SignIn extends React.Component {
    render () {
        return (
            <div id="sign-in-form">

                <div>
                    <form method="post" id="sign-in-form">
                        <div>
                            <label>Enter username</label>
                            <input id="username" type="text" placeholder="username" required={true}/>
                        </div>
                        <div>
                            <label>Enter password</label>
                            <input id="password" type="text" placeholder="password" required={true}/>
                        </div>
                    </form>
                </div>
            </div>
        )
    }
}