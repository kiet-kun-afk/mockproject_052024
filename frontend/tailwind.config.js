/** @type {import('tailwindcss').Config} */
// function withOpacity(variableName) {
// return({opacityValue}) => {
// if (opacityValue !== undefined) {
// return `rgba(var(${variableName}), ${opacityValue})`
// }
// return `rgb(var(${variableName}))`
// }
// }
export default {
	content: [
		'./pages/**/*.{js,jsx}', './components/**/*.{js,jsx}', './app/**/*.{js,jsx}', './src/**/*.{js,jsx}',
	],
	theme: {
		extend: {
			// textColor: {
			// skin: {
			// base: withOpacity('--color-text-base'),
			// muted: withOpacity('--color-text-muted'),
			// inverted: withOpacity('--color-text-inverted')
			// }
			// },
			// backgroundColor: {
			// skin: {
			// fill: withOpacity('--color-fill'),
			// 'button-accent': withOpacity('--color-button-accent'),
			// 'button-accent-hover': withOpacity('--color-button-accent-hover'),
			// 'button-muted': withOpacity('--color-button-muted')
			// }
			// },
			// gradientColorStops: {
			// skin: {
			// hue: withOpacity('--color-fill')
			// }
			// },
			// colors: {
			// '616b76': '#616b76',
			// 'eee': '#eee',
			// 'red-custom': '#e84d1c',
			// 'blue-custom': '#64aed9',
			// 'green-custom': '#67bd3c',
			// 'orange-custom': '#e18604',
			// 'gray-custom': '#798b97',
			// 'turquoise-custom': '#44b1c1'
			// },
			// fontFamily: {
			// 'os': [
			// 'Open Sans', 'sans-serif'
			// ],
			// 'pt': [
			// 'PT Sans Narrow', 'sans-serif'
			// ],
			// 'source-sans-pro': ['Source Sans Pro', 'sans-serif']
			// }
		}
	},
	plugins: []
}
