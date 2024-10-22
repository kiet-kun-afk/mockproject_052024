import { Link } from "react-router-dom";
import logo from "./../../assets/logo.png";

import { CircleUserRound } from 'lucide-react';
import { useEffect, useState } from "react";

const Menu = () => {
    const [user, setUser] = useState({
        email: "",
        password: "",
    });

    useEffect(() => {
        const savedUser = JSON.parse(localStorage.getItem("user"));
        setUser(savedUser);
        console.log(savedUser)
    }, []);
    return (
        <div
            className="z-[100]  h-[90px]  flex justify-between items-center px-[50px]  bg-[rgba(231,231,231,0.5)]
		shadow-[0px_2px_4px_rgba(109,109,109,0.5)]"
        >
            {/* Left Menu */}
            <div className="w-[30%] h-[80%]">
                <Link to="">
                    <img src={logo} alt="" className="h-[100%]" />
                </Link>
            </div>
            {/* Middle Menu */}
            <ul className="flex w-[40%] justify-center gap-[80px] text-[20px]">
                <li className="cursor-pointer">HOME</li>
                <li className="cursor-pointer">BY/RENT</li>
                <li className="cursor-pointer">SELL</li>
            </ul>
            {/* Right Menu */}
            <div className="w-[30%] flex justify-end items-center">
                <div className=" mr-[10px]">
                    <CircleUserRound size={56} />
                </div>
                {user ? (
                    <Link to="/login" className="text-red-500">
                        user.email
                    </Link>
                ) : (
                    <Link to="/login" className="text-red-500">
                        Sign-in/Sign-up
                    </Link>
                )}
            </div>
        </div>
    );
};

export default Menu;