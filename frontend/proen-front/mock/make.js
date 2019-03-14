const fs = require('fs')
const components = ['if', 'while', 'for', 'class', 'print']

const randomComponents = () => {
  components.sort(() => { return Math.random() - 0.5 })
  const len = components.length - 1
  const start = Math.floor(Math.random() * len)
  const end = start === len ? len : Math.floor(Math.random() * (len - start)) + start + 1
  const result = components.slice(start, end)

  return result
}

const base = (num) => {
  return {
    'id': num,
    'title': `Q${num}`,
    'text': `問題${num} 問題${num} 問題${num} 問題${num} 問題${num} 問題${num} 問題${num} 問題${num} 問題${num} 問題${num} 問題${num} 問題${num} 問題${num} 問題${num} 問題${num} 問題${num} 問題${num} 問題${num} 問題${num} 問題${num} 問題${num} 問題${num} 問題${num} 問題${num} 問題${num} 問題${num} 問題${num} 問題${num} 問題${num} 問題${num} 問題${num} 問題${num} 問題${num} 問題${num} 問題${num} 問題${num} 問題${num} 問題${num}`,
    'components': randomComponents()
  }
}

const data = { question: [] }
for (let i = 1; i <= 200; i++) {
  data.question.push(base(i))
}

fs.writeFileSync('./mock.json', JSON.stringify(data, null, 2), 'utf-8', err => console.log(err))
