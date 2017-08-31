import React from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import InteractiveArea from "../components/InteractiveArea";
import PointsTable from "../components/PointsTable";
import CoordinatesPanel from "../components/CoordinatesPanel";
import * as pointActions from "../actions/mainPageActions";

class App extends React.Component {
    render() {
        const {addPoint} = this.props.pointActions;
        return (
            <div>
                <InteractiveArea addPoint={addPoint} points={this.props.points}  />
                <PointsTable points={this.props.points} />
                <CoordinatesPanel addPoint={addPoint}/>
            </div>
        );
    }
}

function mapStateToProps (state) {
    return {
        points: state.points
    }
}

function mapDispatchToProps(dispatch) {
    return {
        pointActions: bindActionCreators (pointActions, dispatch)
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(App)