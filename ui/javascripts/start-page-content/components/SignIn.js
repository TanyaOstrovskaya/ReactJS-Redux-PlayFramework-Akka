import React from "react";

export default class SignIn extends React.Component {
    render () {
        return (
            <div id="sign-in-form">

                <div>
                    <form method="post" action="/users/signin ">
                        <div>
                            <label>Enter login</label>
                            <input name="username" type="text" placeholder="username" required={true}/>
                        </div>
                        <div>
                            <label>Enter password</label>
                            <input name="password" type="text" placeholder="password" required={true}/>
                        </div>
                        <button type="submit" className="btn">submit</button>
                    </form>
                </div>
            </div>
        )
    }
}