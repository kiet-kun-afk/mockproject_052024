import Home from "../pages/Home";
import Login from "../pages/Login";
import NotFound from "../pages/NotFound";
import Screen005 from "../pages/Screen005";
import Screen006 from "../pages/Screen006";
import Screen007 from "../pages/Screen007";

export const routes = [
	{
		path: "/",
		page: Home,
		isShowHeader: true
	},
	{
		path: "/login",
		page: Login,
		isShowHeader: true
	},
	{
		path: "/personal",
		page: Screen005,
		isShowHeader: true
	},
	{
		path: "/list-property",
		page: Screen006,
		isShowHeader: true
	}, {
		path: "/property-infomation/:id",
		page: Screen007,
		isShowHeader: true
	},
	// {
	//     path: "/user",
	//     page: User,
	//     isShowHeader: true,
	//     outlet: [
	//         {
	//             path: "ordersuccess",
	//             page: OrderSuccess
	//         },
	//         {
	//             path: "orderfail",
	//             page: OrderFail
	//         },
	//     ]
	// }
	{
		path: "/*",
		page: NotFound,
		isShowHeader: true
	},
]
