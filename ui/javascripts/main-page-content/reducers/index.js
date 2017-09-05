const initialState = {
    points: [
        {
            x: 0,
            y: 0,
            result: true
        }
    ],
    r: 2
};

export default function pointsState (state = initialState, action) {
    switch (action.type) {
        case 'ADD_POINT' :
            return Object.assign({}, state, {
                points: [
                    ...state.points,
                    {
                        x: action.x,
                        y: action.y,
                        result: action.result
                    }
                ]
            });
        case 'CHANGE_R' :
            return {
                ...state,
                r: action.r
            };

        case 'UPDATE_POINT' :
            return {
                ...state,
                points: state.points.map(point => ((point.x === action.x) && (point.y === action.y)) ?
                    {...point, result: action.result} :
                    point
                )
            };
        default:
            return state;
    }
}