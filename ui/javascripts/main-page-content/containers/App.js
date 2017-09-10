import React from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import InteractiveArea from "../components/InteractiveArea";
import PointsTable from "../components/PointsTable";
import CoordinatesPanel from "../components/CoordinatesPanel";
import * as pointActions from "../actions/mainPageActions";

class App extends React.Component {
    render() {
        return (
            <div className="grid">
                <div className="grid-item grid-item-1">
                    <InteractiveArea sendPoint={this.props.pointActions.sendPoint} points={this.props.points} r={this.props.r} />
                </div>
                <div grid-item grid-item-2></div>
                <div className="grid-item grid-item-3">
                    <PointsTable points={this.props.points} />
                </div>
                <div grid-item grid-item-4></div>
                <div className="grid-item grid-item-5">
                    <CoordinatesPanel points={this.props.points} sendPoint={this.props.pointActions.sendPoint} sendRadius={this.props.pointActions.sendRadius} />
                </div>
            </div>
        );
    }
}

function mapStateToProps (state) {
    return {
        points: state.points,
        r: state.r
    }
}

function mapDispatchToProps(dispatch) {
    return {
        pointActions: bindActionCreators (pointActions, dispatch)
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(App)