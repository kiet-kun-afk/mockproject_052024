import MainHeader from "./MainHeader"

// eslint-disable-next-line react/prop-types
const Header = ({ children }) => {
  return (
    <>
      <MainHeader />
      {children}
    </>
  )
}

export default Header