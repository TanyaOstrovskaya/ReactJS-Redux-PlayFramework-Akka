const initialState = {
    points: [
        {
            x: 0,
            y: 0,
            result: true
        }
    ]
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
            })

        default:
            return state;
    }

}