import React from 'react';

export default class PointsTable extends React.Component {
    constructor (props) {
        super(props);
    }

    render () {
        return (
            <table id="table" className="table table-hover">
                <thead>
                <tr>
                    <td>X</td>
                    <td>Y</td>
                    <td>Result</td>
                </tr>
                </thead>
                <tbody>
                {
                    this.props.points.map(function (point, i) {
                        return (
                            <tr key={i} className={point.result ? "success" : "danger"}>
                                <td>{Math.round(point.x*100)/100}</td>
                                <td>{Math.round(point.y*100)/100}</td>
                                <td>{point.result == true ? "True" : "False"}</td>
                            </tr>
                        )
                    })
                }
                </tbody>
            </table>
        )
    }
}

PointsTable.propTypes = {
    points: React.PropTypes.array.isRequired
}