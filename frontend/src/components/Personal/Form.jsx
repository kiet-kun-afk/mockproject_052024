import { useState } from "react";

// eslint-disable-next-line react/prop-types
const FormField = ({ label, initialValue }) => {
    const [value, setValue] = useState(initialValue);

    const handleChange = (event) => {
        setValue(event.target.value);
    };

    return (
        <div className="mb-4 text-xl">
            <div className="font-bold mb-3 text-[#E6400C] md:text-white ">{label}</div>
            <input type="text" value={value} onChange={handleChange} className="border py-2 px-6  border-gray-300 p-2 text-black w-full rounded-2xl" />
        </div>
    );
};

const PersonalForm = () => {
    return (
        <div className="w-full">
            <div className="text-2xl md:text-5xl mx-auto w-max py-[34px] text-center">
                For your <span className="font-semibold text-[#E6400C]">better life</span>
            </div>
            <div className="w-full bg-white text-[#FF6535] md:bg-[#FF6535] md:text-white md:p-14 pb-6">
                <div className="text-2xl bg-[#FF6535] w-full text-white py-5 flex justify-center font-semibold mb-8 md:text-[64px] mx-auto md:w-max">
                    PERSONAL INFORMATION
                </div>
                <div className="block px-5 md:px-0 md:grid md:grid-cols-2 gap-x-64 gap-y-3">
                    <FormField label="FULLNAME" initialValue="John Doe" />
                    <FormField label="EMAIL" initialValue="john.doe@example.com" />
                    <FormField label="ADDRESS" initialValue="Enter an address, city" />
                    <FormField label="PHONE" initialValue="123-456-7890" />
                </div>
            </div>
        </div>
    );
};

export default PersonalForm;
