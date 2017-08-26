var path = require('path')
var webpack = require('webpack')
var srcPath = path.join(__dirname, 'ui/javascripts')
var destPath = path.join(__dirname, 'public/javascripts')

module.exports = {
    devtool: 'cheap-module-source-map',
    entry: [
        path.join(srcPath, 'start.js'),
        path.join(srcPath, 'main.js')
    ],
    output: {
        path: destPath,
        filename: 'bundle.js',
        publicPath: 'assets'
    },
    plugins: [
        new webpack.optimize.OccurenceOrderPlugin(),
        new webpack.HotModuleReplacementPlugin(),
    ],
    module: {
        loaders: [
            {
                test: /\.js$/,
                loaders: [ 'babel' ],
                exclude: /node_modules/,
                include: __dirname
            },
            {
                test: /\.css$/,
                loaders: [
                    'style-loader',
                    'css-loader?sourceMap&modules&importLoaders=1&localIdentName=[name]__[local]___[hash:base64:5]!postcss?sourceMap&sourceComments',
                ],
            },
        ]
    },
    resolve: {
        extensions: ['', '.js', '.jsx', '.css'],
        modulesDirectories: ["node_modules", srcPath]
    },
    postcss: () => {
        return [
            /* eslint-disable global-require */
            require('postcss-cssnext'),
            /* eslint-enable global-require */
        ];
    }
}