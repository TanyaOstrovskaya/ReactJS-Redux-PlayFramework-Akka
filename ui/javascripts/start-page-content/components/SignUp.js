import React from "react";

export default class SignUp extends React.Component {
    render () {
        return (
            <div id="sign-up-form">
                <div>
                    <form method="post" action="/users/signup">
                        <div>
                            <label>Enter login</label>
                            <input name="username" type="text" placeholder="username" required={true}/>
                        </div>
                        <div>
                            <label>Enter e-mail</label>
                            <input name="email" type="text" placeholder="email" required={true}/>
                        </div>
                        <div>
                            <label>Enter password</label>
                            <input name="password" type="text" placeholder="password" required={true}/>
                        </div>
                        <button type="submit" className="btn btn-default">submit</button>
                    </form>
                </div>
            </div>
        )
    }
}