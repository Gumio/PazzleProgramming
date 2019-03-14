const development = {
  api: {
    base_url: 'http://localhost:4649'
  }
}
const product = {}
const common = {
  title: 'パズプロ（仮）',
  colors: {
    if: 'teal',
    elsif: 'light-green',
    else: 'green',
    while: 'orange',
    for: 'purple',
    class: 'blue',
    print: 'cyan',
    puts: 'indigo',
    variable: 'lime'
  },
  colorCodes: {
    green: '#4CAF50',
    'light-green': '#8BC34A',
    teal: '#009688',
    orange: '#FF9800',
    purple: '#9C27B0',
    blue: '#2196F3',
    cyan: '#00BCD4',
    indigo: '#3F51B5',
    lime: '#CDDC39'
  },
  components: {
    operators: {
      expressions: {
        variable: ['=', '+', '-', '*', '/', '+=', '-=', '*=', '/=']
      },
      wrappers: {
        for: ['in'],
        while: ['==', '!=', '>', '<', '>=', '<='],
        if: ['==', '!=', '>', '<', '>=', '<='],
        elsif: ['==', '!=', '>', '<', '>=', '<=']
      }
    },
    types: {
      expression: {
        VARIABLE: 'VARIABLE',
        PUTS: 'PUTS'
      },
      wrapper: {
        FOR: 'FOR',
        WHILE: 'WHILE',
        IF: 'IF'
      }
    }
  },
  navigation: {
    views: [
      {
        icon: 'fas fa-home',
        title: 'Home',
        to: { name: 'home' }
      },
      {
        icon: 'fas fa-shapes',
        title: 'About',
        to: { name: 'about' }
      },
      {
        icon: 'fas fa-question',
        title: 'Questions',
        to: { name: 'questions' }
      }
    ]
  }
}

const props = process.env.NODE_ENV === 'development' ? development : product
Object.assign(props, common)

export default props
