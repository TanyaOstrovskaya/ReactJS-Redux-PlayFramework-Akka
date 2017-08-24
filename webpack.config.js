var path = require('path')
var webpack = require('webpack')
var srcPath = path.join(__dirname, 'ui/javascripts')
var destPath = path.join(__dirname, 'public/javascripts')

module.exports = {
    entry: [
        path.join(srcPath, 'start.js'),
        path.join(srcPath, 'main.js')
    ],
    output: {
        path: destPath,
        filename: 'bundle.js',
        publicPath: 'assets'
    },
    resolve: {
        extensions: ['', '.js', '.jsx'],
        modulesDirectories: ["node_modules", srcPath]
    },
    module: {
        loaders:[
            {
                test: /\.js$/,
                exclude: /(node_modules)/,
                include: __dirname,
                loader: ["babel-loader"],
                query:{
                    presets:["es2015", "react"]
                }
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
    postcss: () => {
        return [
            /* eslint-disable global-require */
            require('postcss-cssnext'),
            /* eslint-enable global-require */
        ];
    },
}