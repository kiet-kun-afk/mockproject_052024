import { useState, useRef } from 'react';

const HeaderFilter = () => {
    const [beds, setBeds] = useState('');
    const [baths, setBaths] = useState('');
    const bedsInputRef = useRef(null);
    const bathsInputRef = useRef(null);

    const handleChangeBeds = (e) => {
        const newValue = e.target.value;
        const lastChar = newValue[newValue.length - 1];

        if (/^\d$/.test(lastChar)) {
            e.target.value = lastChar;
            setBeds(lastChar);
        } else {
            e.target.value = '';
        }
    };

    const handleChangeBaths = (e) => {
        const newValue = e.target.value;
        const lastChar = newValue[newValue.length - 1];

        if (/^\d$/.test(lastChar)) {
            e.target.value = lastChar;
            setBaths(lastChar);
        } else {
            e.target.value = '';
        }
    };

    return (
        <div className="w-full p-4 border-b-2 border-[#1E1E1E] flex items-center gap-5">
            <div className="relative border-2 rounded-[20px] overflow-hidden w-[290px] bg-[#FCFCFC]">
                <input type="search" id="location-search" className="block px-4 py-5 w-11/12 z-20 text-xl bg-transparent focus:ring-blue-500 focus:outline-none focus:border-blue-500 text-black" placeholder="Add another location" required />
                <button type="submit" className="absolute top-0 end-0 h-full py-5 text-sm font-medium outline-none border-none text-black bg-transparent focus:outline-none">
                    <svg className="w-5 h-5" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 20">
                        <path stroke="currentColor" strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="m19 19-4-4m0-7A7 7 0 1 1 1 8a7 7 0 0 1 14 0Z" />
                    </svg>
                    <span className="sr-only">Search</span>
                </button>
            </div>
            <div className="pe-5 border-2 rounded-[20px] overflow-hidden">
                <select name="cars" id="cars" className="py-5 ps-5 text-xl w-full max-w-36 focus:outline-none">
                    <option value="volvo">For sale</option>
                    <option value="saab">Saab</option>
                    <option value="opel">Opel</option>
                    <option value="audi">Audi</option>
                </select>
            </div>
            <div className="pe-5 border-2 rounded-[20px] overflow-hidden">
                <select name="Price" id="Price" className="py-5 ps-5 text-xl w-full max-w-36 focus:outline-none">
                    <option value="volvo">Price</option>
                    <option value="saab">Saab</option>
                    <option value="opel">Opel</option>
                    <option value="audi">Audi</option>
                </select>
            </div>
            <div className="border-2 rounded-[20px] text-xl w-full max-w-36 flex">
                <div className="pe-5 border-e-2 h-full p-5">
                    Bed
                </div>
                <div className="underline-10px w-10 ps-5 p-4">
                    <input type="text" className="w-5 ms-1 leading-5 text-xl border-none focus:outline-none" value={beds} onChange={handleChangeBeds} ref={bedsInputRef} />
                </div>
            </div>
            <div className="border-2 rounded-[20px] text-xl w-full max-w-36 flex">
                <div className="pe-5 border-e-2 h-full p-5">
                    Bath
                </div>
                <div className="underline-10px w-10 ps-5 p-4">
                    <input type="text" className="w-5 ms-1 leading-5 text-xl border-none focus:outline-none" value={baths} onChange={handleChangeBaths} ref={bathsInputRef} />
                </div>
            </div>
            <div className="pe-5 border-2 rounded-[20px] overflow-hidden">
                <select name="hometype" id="hometype" className="py-5 ps-5 text-xl w-full max-w-40 focus:outline-none">
                    <option value="volvo">Home Type</option>
                    <option value="saab">Saab</option>
                    <option value="opel">Opel</option>
                    <option value="audi">Audi</option>
                </select>
            </div>
            <div className="pe-5 border-2 rounded-[20px] overflow-hidden">
                <select name="view" id="view" className="py-5 ps-5 text-xl w-full max-w-24 focus:outline-none">
                    <option value="volvo">View</option>
                    <option value="saab">Saab</option>
                    <option value="opel">Opel</option>
                    <option value="audi">Audi</option>
                </select>
            </div>
            <div className="p-5 border-2 rounded-[20px] overflow-hidden text-xl w-full max-w-24">
                More
            </div>
            <div className="p-5 border-2 rounded-[20px] overflow-hidden text-xl w-full max-w-24 bg-[#E6400C] text-white flex items-center justify-center">
                Save
            </div>
        </div>
    );
}

export default HeaderFilter;
